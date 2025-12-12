package Service;

import Dao.ContaDao;
import Dao.TitularDao;
import Entidades.Conta;

public class ContaService {

    private TitularDao titularDao;
    private ContaDao contaDao;

    public ContaService(TitularDao titularDao, ContaDao contaDao) {
        this.titularDao = titularDao;
        this.contaDao = contaDao;
    }

    public void mostarContaPorID(long id){
        Conta conta = contaDao.buscarContaPorId(id);
        if (conta != null){
            System.out.println(conta.getTitular().toString());
            System.out.println("");
            System.out.println(conta);

        }
    }
}
