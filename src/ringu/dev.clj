(ns ringu.dev
  (:require [ringu.db.authorities :as au])
  (:require [ringu.conf.util :as co])
  (:require [ringu.db.core :as db]))

(def db db/db-connection)
(def lau au/all-authorities)
(def conf co/get-conf)