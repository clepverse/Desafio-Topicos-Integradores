import java.util.Scanner;

//Aldo Barbosa Migge
//Uninassau

public class Programa {
    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        Fila fila = new Fila();

        int pedidos = 0;
        int pedidosRealizados = 0;

        int opc = 0;
        char esc = 'S';

        do{
            menu();
            opc = sc.nextInt();

            switch(opc) {
                case 1:
                    do {
                        System.out.println("# 1 – INCLUIR PEDIDO   #");
                        pedidos = sc.nextInt();
                        fila.incluir(pedidos);
                        fila.pedidosEmFila();
                        System.out.println("");
                        System.out.println("# DESEJA INCLUIR OUTRO PEDIDO? #");
                        System.out.println("# [S]/[N] #");
                        esc = sc.next().charAt(0);
                    }while (esc == 'S' || esc == 's');
                    break;
                case 2:
                    if(fila.estaVazio()) {
                        System.out.println("Fila vazia");
                    }else{
                        do{
                            if(!fila.estaVazio()) {
                                pedidosRealizados++;
                                fila.pedidosEmFila();
                                System.out.println("Pedido da vez: #"+fila.pedidoDaVez());
                                //fila.listar();
                                System.out.println("");
                                fila.atender();
                                System.out.println("# PEDIDO ATENDIDO #");
                                System.out.println("");
                                //fila.listar();
                                System.out.println("Pedido da vez: #"+fila.pedidoDaVez());
                                System.out.println("# DESEJA ATENDER OUTRO PEDIDO? #");
                                System.out.println("# [S]/[N] #");
                                esc = sc.next().charAt(0);
                            }else{
                                System.out.println("# TODOS PEDIDOS FORAM ATENDIDOS #");
                                new Thread().sleep(2000);
                                break;
                            }
                        }while(esc == 'S' || esc == 's');
                    }
                    break;
                case 3:
                    if (fila.estaVazio()) {
                        System.out.println("Fila vazia");
                        new Thread().sleep(2000);
                    }else{
                        System.out.println("# 3 - LISTA DE PEDIDOS   #");
                        fila.pedidosEmFila();
                        fila.listar();
                        new Thread().sleep(4000);
                        break;
                    }
                    break;
                case 4:
                    System.out.println("# 4 – PESQUISAR PEDIDO #");
                    new Thread().sleep(1000);
                    System.out.println("# NAO IMPLEMENTADO #");
                    new Thread().sleep(1000);
                    break;
                case 5:
                    if (fila.estaVazio()) {
                        System.out.println("# PEDIDOS ATENDIDOS #");
                        System.out.println(pedidosRealizados);
                        System.exit(0);
                    }else{
                        fila.pedidosEmFila();
                        System.out.println("Atenda todos antes de encerrar");
                        System.out.println("");
                        new Thread().sleep(3000);
                    }
                    break;
                default:
                    System.out.println("# OPCAO INDISPONIVEL #");
                    break;
            }

        }while(opc != 0);

        sc.close();
    }

    public static void menu() {
        System.out.println("");
        System.out.println("###### LANCHONETE ######");
        System.out.println("# 1 – INCLUIR PEDIDO   #");
        System.out.println("# 2 - ATENDER PEDIDO   #");
        System.out.println("# 3 - LISTAR PEDIDOS   #");
        System.out.println("# 4 – PESQUISAR PEDIDO #");
        System.out.println("# 5 – ENCERRAR         #");
        System.out.println("########################");
        System.out.println("# DIGITE SUA OPCAO #");
    }

}

class Fila {
    Object[] elemetos = new Object[10];
    int ini = 0;
    int fim = -1;
    int tam = 0;

    void incluir(Object valor) {
        if (tam == elemetos.length) {
            System.out.println("Fila cheia");
            System.out.println("Atenda antes de adicionar mais");
        }else{
            if(fim == elemetos.length - 1) {
                fim = -1;
            }
            fim++;
            elemetos[fim] = valor;
            tam++;
        }
    }

    Object atender() {
        if(!estaVazio()) {
            Object temp = elemetos[ini];
            elemetos[ini] = null;
            ini++;
            if (ini == elemetos.length) {
                ini = 0;
            }
            tam--;
            return temp;
        }else{
            System.out.println("Fila vazia");
            return null;
        }
    }

    boolean estaVazio() {
        return tam == 0;
    }

    int pedidosEmFila() {
        System.out.println("Quantidade de pedidos em fila: " +tam);
        return tam;
    }

    Object pedidoDaVez() {
        if(!estaVazio()) {
            return elemetos[ini];
        }else {
            System.out.println("Fila vazia");
            return null;
        }
    }

    void listar() {
        for (int i = 0; i < tam ; i++) {
            System.out.println(1+i+"º da fila: "+pedidoDaVez() + " ");
            incluir(atender());
        }
    }
}