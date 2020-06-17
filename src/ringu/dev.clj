(ns ringu.dev
  (:require [ringu.db.suppliers :as su])
  (:require [ringu.conf.util :as conf])
  (:require [ringu.db.core :as db])
  (:require [ringu.web.views.helpers :as wh]))

(def db db/db-connection)
(defn lsu [] (su/all-suppliers (db)))
(defn ssu [name] (su/search-by-name-like (db) name))
(def iau su/insert-supplier)
(def iauk su/insert-get-id)
(def get-path wh/get-path)

(comment
  (lsu (db) {:name "ΚΛ ΠΕΙΡΑΙΑ 2"})
  )