server.port=8092
implementeaza:
- H2 database
- rabbit MQ - este listener pt queue "railswitch.queue"

Are 3 tabele (doar primele 2 folosite): RAIL_SWITCHES, TO_BE_CHECK_SWITCHES, CHECKED_SWITCHES (nefolosita)
Sunt contruite pring schema.sql.
Doar tabela RAIL_SWITCHES este populata prin data.sql

Tabela TO_BE_CHECK_SWITCHES este populata in urma trimiterii listei de macazuri de la aplicatia work-manager (rabbit sender).
In clasa RabbitMQConsumer, metoda recievedMessage(), se face receptia mesajului primit de la aplicatia work-amanager, si se populeaza tabela.

Are 2 rest-controllere: 
- RailSwitchController - un singur Rest Api - trimite lista cu toate macazurile existente catre applicatioa work-manager care pregateste listele de macazuri ce trebuiesc verificate bi-lunar (partitionarea macazurilor in liste).
- ToBeCheckSwitchController - mai multe Rest Api-uri, toate folosite doar de app ui-server
    - /to_be_check_switch/partition - aduce lista cu macazurile ce trebuiesc verificate de acar; sunt 6 liste/partitii, toate salvate in tabela TO_BE_CHECK_SWITCHES, fiecare macaz din lista identificat prin campul groupId, care are valorile 1, 2, ..6
    - /to_be_check_switch/set_CurrentGroupId - seteaza lista curenta ce trebuie verificta (currentGroupId)
    - /to_be_check_switch/get_CurrentGroupId - citeste currentGroupId (identificator lista curenta)
    - /to_be_check_switch/mark_as_checked - salveaza macazul verificat (comentariul aferent adaugat de acar si campul boolean ce indica verificarea - checked)
    
    
RabbitMQConsumer - listener-ul de rabbitMQ pt queue "railswitch.queue". metoda recievedMessage() face interceptia mesajului transmis de catre aplicatia work-amanager, si se populeaza tabela ca listele de macazuri trmise de catre work-mamnager

# Important:
app verifserver trebuie startata dupa startarea app work-manager; pt ca work-manager va trebui sa creeze coada queue "railswitch.queue" in cazul cand nu exista (iar la inceput nu va exista).
Alfel, app verifserver se va opri cu eroare pt ca nu gaseste aceasta coada (queue "railswitch.queue")
     

 
