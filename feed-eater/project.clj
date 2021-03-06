(defproject feed-eater "0.1.0-SNAPSHOT"
  :description "Consume the Fetish Feed"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.4"]
                 [lib-noir "0.8.2"]
                 [compojure "1.1.6"]
                 [ring-server "0.3.1"]
                 [selmer "0.6.6"]
                 [com.taoensso/timbre "3.1.6"]
                 [com.taoensso/tower "2.0.2"]
                 [markdown-clj "0.9.43"]
                 [environ "0.4.0"]
                 [com.taoensso/carmine "2.6.2"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [domina "1.0.3-SNAPSHOT"]
                 [shoutout "0.1.0-SNAPSHOT"] ;; The port of FetLife's feature flipper
                 [com.novemberain/welle "2.0.0"]]  ;; a Riak client

  :repl-options {:init-ns feed-eater.repl}
  :plugins [[lein-ring "0.8.10"]
            [lein-environ "0.4.0"]
            [lein-cljsbuild "1.0.3"]
            [com.keminglabs/cljx "0.3.2"]
            [lein-kibit "0.0.8"]]

  :cljx {:builds [
                  {:source-paths ["src"]
                   :output-path "target/classes"
                   :rules :clj}

                  ;;{:source-paths ["src"]
                  ;; :output-path "target/classes"
                  ;; :rules :cljs}
                 ]}

  ;;:hooks [[cljx.hooks]
  ;;        [leiningen.cljsbuild]
  ;;        ]

  ;;:hooks [cljx.hooks leiningen.cljsbuild]
  :hooks [cljx.hooks]

  :cljsbuild
    {:builds [{:source-paths ["target/classes/feed_eater" "src-cljs"]
               :compiler {:output-to "resources/public/js/site.js"
                          :optimizations :advanced
                          :pretty-print true}}]}

  :ring {:handler feed-eater.handler/app
         :init    feed-eater.handler/init
         :destroy feed-eater.handler/destroy}

  :profiles
  {:uberjar {:aot :all}
   :production {:ring {:open-browser? false
                       :stacktraces?  false
                       :auto-reload?  false}}

   :dev {:dependencies [[ring-mock "0.1.5"]
                        [ring/ring-devel "1.2.2"]]
         :env {:dev true}}}

  :min-lein-version "2.0.0")
