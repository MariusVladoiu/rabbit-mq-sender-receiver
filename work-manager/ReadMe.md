server.port=8091
implementeaza rabbit MQ - este sender-ul de mesaje pt queue "railswitch.queue"

Citeste (GET) toate macazurile disponibile din app verifserver si face impartirea (partitionarea) in 6 liste intr-un mod aleator.
Aceste liste (o singura lista ce contine campul groupId cu valori intre 1 si 6) este trimisa prin mesaj rabbitMQ catre app verifserver (prin queue railswitch.queue)
Vezi application.properties pt detalii queue, exchange si routing key.

Intreg mecanism - citire+partitionare+trimite mesaj - este actionat de un Rest Api: /rabbitmq/partitionate (in controller RabbitMQWebController)

Configurare RabbitMW - clasa RabbitMQConfig; folosim DirectExchange pt simplitate 

Trimitere mesaj - clasa RabbitMQSender, metoda send()
