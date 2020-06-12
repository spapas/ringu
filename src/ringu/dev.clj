(ns ringu.dev
  (:require [ringu.db.authorities :as au])
  (:require [ringu.conf.util :as conf])
  (:require [ringu.db.core :as db]))

(def db db/db-connection)
(def lau au/all-authorities)
