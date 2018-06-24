(ns clojure-http-server.core
  (:require [org.httpkit.server :refer [run-server]]
            [clj-time.core :as t]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [cheshire.core :as json]))


(defn- json-response [status body]
  {:status 200 
   :headers {"content-type" "application/json"}
   :body (json/generate-string body)})

(defn get-time
  "Get the current time for the application"
  []
  (let [response {:status 200
                  :body (str (t/time-now))
                  :headers {"content-type" "text/html"}}]
    response))

(defn get-some-json []
  (let [data {:foo "Fooo" :bar "Barr"}]
    {:status 200
     :headers {"content-type" "application/json"}
     :body (json/generate-string data)}))

(defn random-numbers []
  (let [num1 (rand-int 5)
        num2 (rand-int 5)
        num3 (rand-int 5)
       bonus (if (> (rand-int 2) 0) true false)]
    (json-response 200 {:numbers [num1 num2 num3] :bonus bonus})))

(defroutes app
  (GET "/" [] "<h1> Welcome <h1>")
  (GET "/get-time" [] (get-time))
  (GET "/foo.json"[] (get-some-json))
  (GET "/random-numbers.json" [] (random-numbers))
  (route/not-found "<h1> Page not found </h1>"))

(defn -main [& args]
    (run-server app {:port 8080})
    (println "server started at port 8080"))
