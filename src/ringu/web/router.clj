(ns ringu.web.router
  (:require
   [ringu.web.exception :as exception]
   [reitit.ring :as ring]
   ;[ring.middleware.params :refer [wrap-params]]
   ;[ring.middleware.keyword-params :refer [wrap-keyword-params]]
   [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
   [ring.middleware.session.cookie :refer [cookie-store]]
   [ringu.web.handlers :as handlers]
   [ringu.web.views.home :as home]
   [ringu.web.views.suppliers :as suppliers]))

(defn handler [_]
  {:status 200, :body "ok!!zzzz"})

(defn handler_id [{{id :id} :path-params}]
  {:status 200, :body (str "zz " id)})

(def routes [["/" {:get (handlers/html-resp home/index-page) :name :home}]
             ["/suppliers/" {:get (handlers/html-resp suppliers/index-page) :name :suppliers}]
             ["/suppliers/add/" {
                                 :get (handlers/html-resp suppliers/add) 
                                 :post (handlers/html-resp suppliers/add-post) 
                                 :name :suppliers-add}]
             ["/fail/" (fn [_] (throw (ex-info "fail" {:type :failure})))]
             ["/pang/:id" {:get handler_id :name :pang}]
             ["/pong/" {:get handler :name :pong}]
             ["/ping/" {:get handler :name :ping}]])

(defn sample-middleware [handler]
  (fn [request]
    (handler (assoc request :koko "KOKO"))))

(def the-site-defaults
  (merge site-defaults
         {:session {:store (cookie-store {:key "0123456789ABCDEF"})}}))

(def router (ring/router
             routes
             {:data {:middleware [sample-middleware
                                  ;wrap-params
                                  ;wrap-keyword-params
                                  #(wrap-defaults % the-site-defaults)
                                  exception/exception-middleware]}}))
