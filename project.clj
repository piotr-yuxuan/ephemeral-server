(defproject piotr-yuxuan/ephemeral-server (-> "./resources/ephemeral-server.version" slurp .trim)
  :description "A small, closeable test server to catch http calls you don't mock"
  :url "https://github.com/piotr-yuxuan/ephemeral-server"
  :license {:name "GNU General Public License v3.0 or later"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"
            :distribution :repo
            :comments "See also GPL_ADDITION.org"}
  :scm {:name "git"
        :url "https://github.com/piotr-yuxuan/ephemeral-server"}
  :pom-addition [:developers [:developer
                              [:name "胡雨軒 Петр"]
                              [:url "https://github.com/piotr-yuxuan"]]]
  :global-vars {*warn-on-reflection* true}
  :plugins [[lein-tools-deps "0.4.5"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files [:project]})
