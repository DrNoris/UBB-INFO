package ubb.scs.map;

import ubb.scs.map.domain.validators.PrieteniValidator;
import ubb.scs.map.domain.validators.UtilizatorValidator;
import ubb.scs.map.repository.file.PritenieRepository;
import ubb.scs.map.repository.file.UtilizatorRepository;
import ubb.scs.map.service.Service;
import ubb.scs.map.ui.ui;

public class Main {
    public static void main(String[] args) {
        UtilizatorRepository uRepo = new UtilizatorRepository(new UtilizatorValidator(), "data/utilizatori.txt");
        PritenieRepository pRepo = new PritenieRepository(new PrieteniValidator(), "data/prietenii.txt");

        Service service = new Service(pRepo, uRepo);

        ui ui = new ui(service);

        ui.menu();
    }
}