(ns db.migrations
  (:require [migratus.core :as migratus])
  (:require [clojure.java.jdbc :as jdbc])
  (:require [ringu.conf.util :as conf])
  (:require [ringu.db.core :as db]))

(defn get-conn [] (.getConnection (:datasource (db/db-connection))) )

(defn config [] {
             :store :database
             :migration-dir        "migrations/"
             :migration-table-name "migrations"
             :db {:connection (get-conn)}
             })

;initialize the database using the 'init.sql' script
;(migratus/init config)

;apply pending migrations
;(migratus/migrate config)

;rollback the last migration applied
;(migratus/rollback config)

;bring up migrations matching the ids
;(migratus/up config 20111206154000)

;bring down migrations matching the ids
;(migratus/down config 20111206154000)