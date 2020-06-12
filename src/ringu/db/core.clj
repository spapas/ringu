(ns ringu.db.core
  (:require [ringu.conf.util :as conf])
  (:import (com.mchange.v2.c3p0 ComboPooledDataSource)))

(def pg-db-spec {:classname "org.postgresql.Driver"
                 :jdbcUrl (conf/get-conf :db-url)
                 :user (conf/get-conf :db-user)
                 :password (conf/get-conf :db-password)})

(defn pool
  [spec]
  (let [cpds (doto (ComboPooledDataSource.)
               (.setDriverClass (:classname spec))
               (.setJdbcUrl (:jdbcUrl spec))
               (.setUser (:user spec))
               (.setAcquireRetryAttempts 5)
               (.setPassword (:password spec))
               ;; expire excess connections after 30 minutes of inactivity:
               (.setMaxIdleTimeExcessConnections (* 30 60))
               ;; expire connections after 3 hours of inactivity:
               (.setMaxIdleTime (* 3 60 60)))]
    {:datasource cpds}))

(def pooled-db (delay (pool pg-db-spec)))
(defn db-connection [] @pooled-db)
