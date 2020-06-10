(ns ringu.dev
  (:require [ringu.db.authorities :as au])
  (:require [ringu.db.core :as db]))

(def db db/db-connection)
(def lau au/all-authorities)
