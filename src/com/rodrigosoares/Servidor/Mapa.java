package com.rodrigosoares.Servidor;

import java.util.ArrayList;
import java.util.Stack;

public class Mapa {
    private ArrayList<Cidade> cidades;
    private Stack< ArrayRotas > stack = new Stack<>();


    public Mapa(ArrayList<Cidade> cidades) throws Exception{
        if(cidades == null)
            throw new Exception ("Cidades invalidas!");

        this.cidades = new ArrayList<>(cidades);

    }

    public ArrayList<Cidade> getCidades(){
        return this.cidades;
    }

    public Cidade getCidade(String nome)throws Exception{
        for (int i=0; i<cidades.size(); i++){
            if(cidades.get(i).getNome().equals(nome))
                return cidades.get(i);
        }
        throw new Exception("Cidade inexistente!");
    }

    public ArrayRotas menorRota(String nomeOrigem, long tempoDebusca) throws Exception{
        return menorRota(nomeOrigem, this.cidades, tempoDebusca);
    }

    public ArrayRotas menorRota(String nomeOrigem, ArrayList<Cidade> destinos, long tempoDeBusca) throws Exception{
        int menorDistancia=999999999;
        ArrayRotas menorRota = null;
        Cidade origem = getCidade(nomeOrigem);
        ArrayRotas rotaAtual;
        ArrayRotas rotasDaOrigem = new ArrayRotas(origem.getRotas());
        long startTime = System.currentTimeMillis();
        long timeRunning = System.currentTimeMillis()-startTime;
        boolean rotaEncontrada = false;
        tempoDeBusca = tempoDeBusca * 1000; //Segundos -> Milisegundos

        //Adiciona todas as rotas da origem na pilha
        for (int i=0 ; i<rotasDaOrigem.size(); i++){
            ArrayRotas novaRota = new ArrayRotas();
            novaRota.add(rotasDaOrigem.get(i));
            stack.add(novaRota);
        }

        //Verifica se todas as rotas da pilha já foram testadas && (nenhuma rota foi encontrada || o tempor é menor que 3 minutos)
        while (!stack.empty() && (!rotaEncontrada || timeRunning<tempoDeBusca)){
            rotaAtual = new ArrayRotas(stack.pop());

            //Verifica se a rota atual passa por todas as cidades (nós) desejados e a última cidade é a origem
            //printaRotas(rotaAtual);
            if(rotaAtual.get(rotaAtual.size()-1).getDestino()==origem && contemTodasAsCidades(rotaAtual, destinos)){
                if (rotaAtual.getDistancia() < menorDistancia) {
                    menorDistancia = rotaAtual.getDistancia();
                    menorRota = rotaAtual;
                    rotaEncontrada = true;
                }
                //return menorRota;
            }

            else{
                ArrayRotas proximasRotas = new ArrayRotas(rotaAtual.get(rotaAtual.size()-1).getDestino().getRotas());

                //Adiciona todas as rotas da ultima cidade (nó) visitada na pilha
                for (int i=0 ; i<proximasRotas.size(); i++) {
                    ArrayRotas novaRota = new ArrayRotas(rotaAtual);

                    //Verifica se a rota a ser adicionada na pilha não contem cidades repetidas
                    if(rotaAtual.getDistancia()<menorDistancia && !contemCidadesRepetidas(rotaAtual, origem)) {
                        proximasRotas.get(i).destino.visitada = true;
                        proximasRotas.get(i).getDestino();
                        novaRota.add(proximasRotas.get(i));
                        stack.add(novaRota);
                    }
                }
            }
            timeRunning = System.currentTimeMillis()-startTime;
            //System.out.println(timeRunning +"|"+menorRota+"|"+Boolean.toString(!stack.empty() && (menorRota==null || timeRunning<180000)));
        }
        return menorRota;
    }

    //Verifica se a rota possue cidades (nós) repetidas
    private boolean contemCidadesRepetidas(ArrayRotas rota, Cidade origem) throws Exception{
        ArrayList<Cidade> cidadesContidas = new ArrayList<>();
        cidadesContidas.add(origem);
        for(int i=0; i<rota.size(); i++){
            Cidade cidadeAtual = rota.get(i).getDestino();
            /*Verifica se a cidade é isolada
            if(cidadeAtual.getRotas().size() == 1){
                cidadeAtual.getRotas().get(0).getDestino().setVizinhaDeIsolada(true);
            }
            else
                cidadeAtual.getRotas().get(0).getDestino().setVizinhaDeIsolada(false);
            */
            //Verifica se a cidade está repetida
            if (/*(!cidadeAtual.isVizinhaDeIsolada()) &&*/ cidadesContidas.contains(cidadeAtual))
                return true;
            else
                //Adiciona a cidade nos Array de cidades da rota, somente se não for vizinha de alguma cidade isolada
                //if(!cidadeAtual.isVizinhaDeIsolada())
                    cidadesContidas.add(cidadeAtual);
        }
        return false;
    }

    //Verifica se a rota passa por todas as cidades (nós) do mapa
    public boolean contemTodasAsCidades(ArrayRotas rotas, ArrayList<Cidade> destinos){
        ArrayList<Cidade> cidadesCopia = new ArrayList<>(destinos);
        ArrayList<Cidade> cidadesNaRota = new ArrayList<>();
        for (int i=0; i<rotas.size(); i++){
            cidadesNaRota.add(rotas.get(i).destino);
        }

        cidadesCopia.removeAll(cidadesNaRota);

        if(cidadesCopia.isEmpty())
            return true;

        return false;
    }

    private void printaRotas(ArrayRotas rotas) throws Exception{
        int i;
        System.out.print("[");
        for (i=0; i<rotas.size()-1; i++)
            System.out.print(rotas.get(i).getDestino().getNome()+", ");

        System.out.print(rotas.get(i).getDestino().getNome()+"]\n");
    }
}
