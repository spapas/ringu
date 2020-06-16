(defproject ringu "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.8.1"]
                 [ring/ring-devel "1.8.1"]
                 [ring/ring-jetty-adapter "1.8.1"]
                 [com.layerware/hugsql "0.5.1"]
                 [org.postgresql/postgresql "42.2.2"]
                 [funcool/struct "1.3.0"]
                 [org.xerial/sqlite-jdbc "3.31.1"]
                 [com.mchange/c3p0 "0.9.5.5"]
                 [com.taoensso/timbre "4.10.0"]
                 [hiccup "1.0.5"]
                 [migratus/migratus "1.2.8"]
                 ;; Dep for migratus
                 [com.fzakaria/slf4j-timbre "0.3.19"]
                 [metosin/reitit "0.5.2"]]
  :repl-options {:init-ns ringu.core}
  
  )
