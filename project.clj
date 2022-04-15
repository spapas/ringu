(defproject ringu "0.1.0-SNAPSHOT"
  :description "A CRUD web-app in clojure using ring and friends"
  :url "https://github.com/spapas/ringu"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :managed-dependencies [[ring/ring-core "1.9.5"]
                         [org.clojure/java.jdbc "0.7.12"]
                         [borkdude/edamame "0.0.19"]
                         [org.clojure/spec.alpha "0.3.218"]]
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/data.json "2.4.0"]
                 [ring/ring-devel "1.9.5"]
                 [ring/ring-defaults "0.3.3"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [com.layerware/hugsql "0.5.3"]
                 [org.postgresql/postgresql "42.3.3"]
                 [funcool/struct "1.4.0"]
                 [org.xerial/sqlite-jdbc "3.36.0.3"]
                 [com.mchange/c3p0 "0.9.5.5"]
                 [com.taoensso/timbre "5.2.1"]
                 [hiccup "1.0.5"]
                 [migratus/migratus "1.3.6"]
                 ;; Dep for migratus
                 [com.fzakaria/slf4j-timbre "0.3.21"]
                 [metosin/reitit "0.5.18"]]
  :plugins [[lein-ancient "1.0.0-RC4-SNAPSHOT"]]  
  :repl-options {:init-ns ringu.core})
