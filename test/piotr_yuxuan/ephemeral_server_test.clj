(ns piotr-yuxuan.ephemeral-server-test
  (:require [piotr-yuxuan.ephemeral-server :as ephemeral-server]
            [aleph.http :as http]
            [clojure.test :refer [deftest testing is]]))

(deftest ephemeral-server-test
  (let [expected "youp"
        port (atom nil)]
    (with-open [_ (ephemeral-server/open port ["/ping" {:get (constantly {:body expected})}])]
      (let [actual (->> (http/get (format "http://localhost:%s/ping" @port))
                        deref
                        :body
                        slurp)]
        (is (= expected actual))))))
