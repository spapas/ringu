(ns ringu.web.router
  (:require
   [ringu.web.exception :as exception]
   [reitit.ring :as ring]

   [ringu.web.handlers :as handlers]
   [ringu.web.views.home :as home]
   [ringu.web.views.suppliers :as suppliers]))

(defn handler [_]
  {:status 200, :body "ok!!zzzz"})

(defn handler_id [{{id :id} :path-params}]
  {:status 200, :body (str "zz " id)})

(def routes [["/" {:get (handlers/html-resp home/index-page) :name :home}]
             ["/suppliers/" {:get (handlers/html-resp suppliers/index-page) :name :suppliers}]
             ["/suppliers/add/" {:get (handlers/html-resp suppliers/add)
                                 :post (handlers/html-resp suppliers/add-post)
                                 :name :suppliers-add}]
             ["/fail/" (fn [_] (throw (ex-info "fail" {:type :failure})))]
             ["/pang/:id" {:get handler_id :name :pang}]
             ["/pong/" {:get handler :name :pong}]
             ["/ping/" {:get handler :name :ping}]])





(def router (ring/router
             routes
             {:data {:middleware [exception/exception-middleware]}}))
