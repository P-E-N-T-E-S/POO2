// Source code is decompiled from a .class file using FernFlower decompiler.
package br.edu.cesarschool.cc.poo.ac.passagem;

import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;

public class VooMediator {
   private static VooMediator instance;
   private VooDAO vooDAO = new VooDAO();
   private String[] aeroportosValidos = new String[]{"GRU", "CGH", "GIG", "SDU", "REC", "CWB", "POA", "BSB", "SSA", "FOR", "MAO", "SLZ", "CNF", "BEL", "JPA", "PNZ", "CAU", "FEN", "SET", "NAT", "PVH", "BVB", "FLN", "AJU", "PMW", "MCZ", "MCP", "VIX", "GYN", "CGB", "CGR", "THE", "RBR", "VCP", "RAO"};

   private boolean validarAeroporto(String aeroporto) {
      String[] var2 = this.aeroportosValidos;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String aeroportoValido = var2[var4];
         if (aeroportoValido.equals(aeroporto)) {
            return true;
         }
      }

      return false;
   }

   private VooMediator() {
   }

   public static synchronized VooMediator obterInstancia() {
      if (instance == null) {
         instance = new VooMediator();
      }

      return instance;
   }

   public Voo buscar(String voo) {
      return this.vooDAO.buscar(voo);
   }

   public String validarCiaNumero(String companhiaAerea, int numeroVoo) {
      boolean validacaoCia = StringUtils.isVaziaOuNula(companhiaAerea) && companhiaAerea.length() == 2;
      if (!validacaoCia) {
         return "CIA aerea errada";
      } else {
         boolean validacaoNumero = numeroVoo >= 1000 && numeroVoo <= 9999;
         return !validacaoNumero ? "Numero voo errado" : null;
      }
   }

   public String validar(Voo voo) {
      boolean validarAeroportoOrigem = StringUtils.isVaziaOuNula(voo.getAeroportoOrigem()) && this.validarAeroporto(voo.getAeroportoOrigem());
      if (!validarAeroportoOrigem) {
         return "Aeroporto origem errado";
      } else {
         boolean validarAeroportoDestino = StringUtils.isVaziaOuNula(voo.getAeroportoDestino()) && this.validarAeroporto(voo.getAeroportoDestino());
         if (!validarAeroportoDestino) {
            return "Aeroporto destino errado";
         } else {
            boolean validarAeroportoOrigemEDestino = voo.getAeroportoDestino().equals(voo.getAeroportoOrigem());
            if (validarAeroportoOrigemEDestino) {
               return "Aeroporto origem igual a aeroporto destino";
            } else {
               String erro = this.validarCiaNumero(voo.getCompanhiaAerea(), voo.getNumeroVoo());
               return erro != null ? erro : null;
            }
         }
      }
   }

   public String incluir(Voo voo) {
      String erro = this.validar(voo);
      if (erro == null) {
         boolean verificacao = this.vooDAO.incluir(voo);
         return verificacao ? null : "Voo ja existente";
      } else {
         return erro;
      }
   }

   public String alterar(Voo voo) {
      String erro = this.validar(voo);
      if (erro == null) {
         boolean verificacao = this.vooDAO.alterar(voo);
         return verificacao ? null : "Voo inexistente";
      } else {
         return erro;
      }
   }

   public String excluir(String idvoo) {
      Voo voo = this.buscar(idvoo);
      if (voo == null) {
         return "Voo inexistente";
      } else {
         String erro = this.validar(voo);
         if (erro == null) {
            boolean verificacao = this.vooDAO.excluir(voo.obterIdVoo());
            return verificacao ? null : "Voo inexistente";
         } else {
            return erro;
         }
      }
   }
}
