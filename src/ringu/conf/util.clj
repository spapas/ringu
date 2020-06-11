(ns ringu.conf.util
  (:require clojure.edn clojure.java.io clojure.string)
  )

(defn read-conf [path]
  (-> (or 
       (clojure.java.io/resource path) (clojure.java.io/file path))
      (slurp) (clojure.edn/read-string)))

(def config (atom (merge (read-conf "ringu/conf/base.edn")
                         (read-conf "ringu/conf/local.edn")
                         )))

(defn to-env [s]
  (-> s (clojure.string/replace "-" "_") (.toUpperCase)))

(defn get-conf [name]
  (or (System/getenv (to-env name)) (get-in @config [name]) ""))