(defproject piotr-yuxuan/ephemeral-server (-> "./resources/ephemeral-server.version" slurp .trim)
  :description "A small, closeable test server to catch http calls you don't mock"
  :github/private? false
  :url "https://github.com/piotr-yuxuan/ephemeral-server"
  :license {:name "European Union Public License 1.2 or later"
            :url "https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12"
            :distribution :repo}
  :scm {:name "git"
        :url "https://github.com/piotr-yuxuan/ephemeral-server"}
  :pom-addition [:developers [:developer
                              [:name "胡雨軒 Петр"]
                              [:url "https://github.com/piotr-yuxuan"]]]
  :global-vars {*warn-on-reflection* true}
  :dependencies [[metosin/reitit-ring "0.7.0"]
                 [aleph/aleph "0.8.0"]
                 [org.clojure/clojure "1.12.0-beta1"]]
  :deploy-repositories [["clojars" {:sign-releases false
                                    :url "https://clojars.org/repo"
                                    :username :env/WALTER_CLOJARS_USERNAME
                                    :password :env/WALTER_CLOJARS_PASSWORD}]
                        ["github" {:sign-releases false
                                   :url "https://maven.pkg.github.com/piotr-yuxuan/ephemeral-server"
                                   :username :env/GITHUB_ACTOR
                                   :password :env/WALTER_GITHUB_PASSWORD}]])
