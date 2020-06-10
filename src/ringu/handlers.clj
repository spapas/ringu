(ns ringu.handlers
  (:require [ring.util.response :refer [response]]))            
            
            


(defn home [_]
  (response "HELLO HOME"))
