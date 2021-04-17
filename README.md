# `ephemeral-server`

One-function library that helps to open ephemeral servers.

[![](https://img.shields.io/clojars/v/piotr-yuxuan/ephemeral-server.svg)](https://clojars.org/piotr-yuxuan/ephemeral-server)
[![cljdoc badge](https://cljdoc.org/badge/piotr-yuxuan/ephemeral-server)](https://cljdoc.org/d/piotr-yuxuan/ephemeral-server/CURRENT)
[![GitHub license](https://img.shields.io/github/license/piotr-yuxuan/ephemeral-server)](https://github.com/piotr-yuxuan/ephemeral-server/blob/main/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/piotr-yuxuan/ephemeral-server)](https://github.com/piotr-yuxuan/ephemeral-server/issues)

In your project, require:

``` clojure
(require '[piotr-yuxuan.ephemeral-server :as ephemeral-server])
```

You now may open an ephemeral server that will live a short but
beautiful life ✨.

``` clojure
(let [port (atom nil]
  (with-open [_ (ephemeral-server/open port ["/ping" {:get (constantly {:body "expected"})}])]
    ;; Now @port is set to its actual value.
    (->> (http/get (format "http://localhost:%s/ping" @port))
         deref
         :body
         slurp)))
```
