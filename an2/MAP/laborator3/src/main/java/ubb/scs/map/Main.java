package ubb.scs.map;

import ubb.scs.map.domain.Prietenie;
import ubb.scs.map.domain.Tuple;
import ubb.scs.map.domain.Utilizator;
import ubb.scs.map.domain.validators.UtilizatorValidator;
import ubb.scs.map.repository.Repository;
import ubb.scs.map.repository.database.UtilizatorDatabaseRepository;
import ubb.scs.map.repository.database.PrietenieDatabaseRepository;
import ubb.scs.map.service.Service;
import ubb.scs.map.ui.ui;

public class Main {
    public static void main(String[] args) {
        Repository<Long, Utilizator> uRepo = new UtilizatorDatabaseRepository("postgres", "noris2580",
                "jdbc:postgresql://localhost:5432/postgres", new UtilizatorValidator());
        Repository<Tuple<Long, Long>, Prietenie> pRepo = new PrietenieDatabaseRepository("postgres", "noris2580",
                "jdbc:postgresql://localhost:5432/postgres");
        Service service = new Service(pRepo, uRepo);

        ui ui = new ui(service);

        ui.menu();
    }
}