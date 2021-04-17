(ns piotr-yuxuan.ephemeral-server
  "In your project, require:

  ``` clojure
  (require '[piotr-yuxuan.ephemeral-server :as ephemeral-server])
  ```

  You now may open an ephemeral server that will live a short but
  beautiful life ✨.

  ``` clojure
  (let [port (atom nil]
    (with-open [_ (ephemeral-server/open port [\"/ping\" {:get (constantly {:body \"expected\"})}])]
      ;; Now @port is set to its actual value.
      (->> (http/get (format \"http://localhost:%s/ping\" @port))
           deref
           :body
           slurp)))
  ```"
  (:require [aleph.http :as http]
            [reitit.ring :as ring])
  (:import (java.io Closeable)
           (java.net BindException ServerSocket)))

(defn ephemeral-port
  "https://en.wikipedia.org/wiki/Ephemeral_port"
  []
  (let [socket (ServerSocket. 0)]
    (.close socket)
    (.getLocalPort socket)))

(defn ^Closeable open
  "Takes two arguments, an empty atom `port` and reitit definition of
  ring `routes`. Return a `Closeable` server with given `routes` on
  first available port and set the atom `port` on the value being
  used."
  ([port route-data] (open port route-data nil))
  ([port route-data {:keys [route handler]}]
   (let [handler (ring/ring-handler
                   (ring/router route-data (:opts route))
                   (or (:default-handler handler)
                       #(throw (ex-info "Path not found in ephemeral server" (into {} %))))
                   (:opts handler))]
     (try
       (reset! port (ephemeral-port))
       (http/start-server handler {:port @port})
       (catch BindException _
         (open port route-data))))))
