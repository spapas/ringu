(ns ringu.dev
  (:require [ringu.db.authorities :as au])
  (:require [ringu.conf.util :as conf])
  (:require [ringu.db.core :as db]))

(def db db/db-connection)
(defn lau [] (au/all-authorities (db)))
(def iau au/insert-authority)
(def iauk au/insert-authority-return-key)

(comment
  (iau (db) {:name "ΚΛ ΠΕΙΡΑΙΑ 2"})
  )