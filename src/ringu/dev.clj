(ns ringu.dev
  (:require [ringu.db.suppliers :as au])
  (:require [ringu.conf.util :as conf])
  (:require [ringu.db.core :as db])
  (:require [ringu.web.views.helpers :as wh]))

(def db db/db-connection)
(defn lau [] (au/all-suppliers (db)))
(def iau au/insert-supplier)
(def iauk au/insert-get-id)
(def get-path wh/get-path)

(comment
  (iau (db) {:name "ΚΛ ΠΕΙΡΑΙΑ 2"})
  )