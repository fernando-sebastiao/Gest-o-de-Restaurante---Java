import java.io.*;
import java.util.ArrayList;

public class DefesaFile {
    private final String nomeArquivo = "DefesaData.dat";

    public DefesaFile() {
        // Se quiser, pode criar o arquivo vazio se não existir
        File f = new File(nomeArquivo);
        if (!f.exists()) {
            salvarLista(new ArrayList<DefesaModelo>());
        }
    }

    public ArrayList<DefesaModelo> listarDados() {
        ArrayList<DefesaModelo> lista = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            lista = (ArrayList<DefesaModelo>) ois.readObject();
        } catch (Exception e) {
            lista = new ArrayList<>();
        }
        return lista;
    }

    public void salvar(DefesaModelo defesa) {
        ArrayList<DefesaModelo> lista = listarDados();
        lista.add(defesa);
        salvarLista(lista);
    }

    public void eliminarDados(DefesaModelo defesa) {
        ArrayList<DefesaModelo> lista = listarDados();
        lista.removeIf(d -> d.getId() == defesa.getId());
        salvarLista(lista);
    }

    public void salvarLista(ArrayList<DefesaModelo> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Buscar defesa por id
    public static DefesaModelo getDefesaPorId(int id) {
        DefesaFile df = new DefesaFile();
        for (DefesaModelo d : df.listarDados()) {
            if (d.getId() == id)
                return d;
        }
        return null;
    }

    // Método para pegar o próximo código disponível (incremental)
    public int getProximoCodigo() {
        ArrayList<DefesaModelo> lista = listarDados();
        int maxId = 0;
        for (DefesaModelo d : lista) {
            if (d.getId() > maxId)
                maxId = d.getId();
        }
        return maxId + 1;
    }
}
