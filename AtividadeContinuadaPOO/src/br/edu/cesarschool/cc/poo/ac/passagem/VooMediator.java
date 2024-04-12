package br.edu.cesarschool.cc.poo.ac.passagem;
import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;

import java.util.Objects;

public class VooMediator {
    VooDAO vooDao;

    public Voo buscar(String IdVoo) {
        return vooDao.buscar(IdVoo);
    }

    public String validarCiaNumero(String companhiaAerea, int numeroVoo) {
        if (StringUtils.isVaziaOuNula(companhiaAerea) && companhiaAerea.length() < 2) {
            return "CIA aerea errada";
        }

        if (numeroVoo < 0 || numeroVoo < 1000 || numeroVoo > 9999) {
            return "Numero voo errado";
        }

        return null;
    }

    public String validar(Voo voo) {
        String[] aeroportos = {
                "GRU", "CGH", "GIG", "SDU", "REC", "CWB",
                "POA", "BSB", "SSA", "FOR", "MAO", "SLZ",
                "CNF", "BEL", "JPA", "PNZ", "CAU", "FEN",
                "SET", "NAT", "PVH", "BVB", "FLN", "AJU",
                "PMW", "MCZ", "MCP", "VIX", "GYN", "CGB",
                "CGR", "THE", "RBR", "VCP", "RAO"
        };

        boolean origem = false;
        boolean destino = false;
        for (int i = 0; i < aeroportos.length; i++) {
            if (aeroportos[i].compareTo(voo.getAeroportoOrigem()) == 0) {
                origem = true;
            }

            if (aeroportos[i].compareTo(voo.getAeroportoDestino()) == 0) {
                destino = true;
            }
        }

        if (StringUtils.isVaziaOuNula(voo.getAeroportoOrigem()) && !origem) {
            return "Aeroporto origem errado";
        }

        if (StringUtils.isVaziaOuNula(voo.getAeroportoDestino()) && !destino) {
            return "Aeroporto destino errado";
        }

        if (Objects.equals(voo.getAeroportoOrigem(), voo.getAeroportoDestino())) {
            return "Aeroporto origem igual a aeroporto destino";
        }
    }

    public String incluir(Voo voo) {
        if (validar(voo) != null) {
            return validar(voo);
        }
        else {
            if (!vooDao.incluir(voo)) {
                return "Voo ja existente";
            }
            else {
                return null;
            }
        }
    }

    public String alterar(Voo voo) {
        if (validar(voo) != null) {
            return validar(voo);
        }
        else {
            if (!vooDao.alterar(voo)) {
                return "Voo existente";
            }
            else {
                return null;
            }
        }
    }

    public String excluir(String idVoo) {
        if (StringUtils.isVaziaOuNula(idVoo)) {
            return "Id voo errado";
        }
        else {
            if (!vooDao.excluir(idVoo)) {
                return "Voo inexistente";
            }
            else {
                return null;
            }
        }
    }
}
