(ns ringu.core
  (:require [reitit.ring :as ring]
            [ring.adapter.jetty :refer [run-jetty]]
            [ringu.web.router :refer [router]]
            [ring.middleware.flash :refer [wrap-flash]]
            [ring.middleware.session.memory :refer [memory-store ]]
            [ring.middleware.session.cookie :refer [cookie-store]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn sample-middleware [handler]
  (fn [request]
    (handler (assoc request :koko "KOKO"))))

(def the-site-defaults
  (merge site-defaults {}
         ;{:session {:store (memory-store) }}
         {:session {:store (cookie-store {:key (byte-array (map (comp byte int) "0123456789ABCDEF"))})}}
         ))

(def app
  (->  (ring/ring-handler
        router
        (ring/create-default-handler))
       wrap-flash
       sample-middleware
       (wrap-defaults the-site-defaults)
       ))

(def reloadable-app (wrap-reload  #'app))

(defonce server (run-jetty reloadable-app {:port 3000 :join? false}))
