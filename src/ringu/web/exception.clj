(ns ringu.web.exception
  (:require [reitit.ring.middleware.exception :as exception]
            [taoensso.timbre :as timbre
             :refer [log  trace  debug  info  warn  error  fatal  report
                     logf tracef debugf infof warnf errorf fatalf reportf
                     spy get-env]])) 
            

(defn ex_handler [message exception request]
  (error message)
  (.printStackTrace exception)
  {:status 500
   :body (str {:message message
               :exception (.getClass exception)
               :data (ex-data exception)
               :uri (:uri request)})})

(def exception-middleware
  (exception/create-exception-middleware
    (merge
      exception/default-handlers
      {
       ::error (partial ex_handler "error")
       ::exception (partial ex_handler "exception")
       ::failure (partial ex_handler "Failure!")
       java.sql.SQLException (partial ex_handler "sql-exception")
       ::exception/default (partial ex_handler "default")
       ::exception/wrap (fn [handler e request]
                          (println "ERROR" (pr-str (:uri request)))
                          (handler e request))})))
