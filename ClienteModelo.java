/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: ClienteModelo.java
Data: 10.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ClienteModelo implements RegistGeneric
{
    private int id;
    private StringBufferModelo nome, contacto, genero, nacionalidade, provincia, municipio, comuna;
    private boolean status;
    
    public ClienteModelo()
    {
        id = 0;
        nome = new StringBufferModelo("", 50);
        contacto = new StringBufferModelo("", 20);
        genero = new StringBufferModelo("", 10);
        nacionalidade = new StringBufferModelo("", 10);
        provincia = new StringBufferModelo("", 20);
        municipio = new StringBufferModelo("", 20);
        comuna = new StringBufferModelo("", 20);
        status = false;
    }

    public ClienteModelo(int id, String nome, String contacto, String genero, String nacionalidade, 
    String provincia, String municipio, String comuna, boolean status)
    {
        this.id = id;
        this.nome = new StringBufferModelo(nome, 50);
        this.contacto = new StringBufferModelo(contacto, 20);
        this.genero = new StringBufferModelo(genero, 10);
        this.nacionalidade = new StringBufferModelo(nacionalidade, 10);
        this.provincia = new StringBufferModelo(provincia, 20);
        this.municipio = new StringBufferModelo(municipio, 20);
        this.comuna = new StringBufferModelo(comuna, 20);
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getNome()
    {
        return nome.toStringEliminatingSpaces();
    }

    public String getContacto()
    {
        return contacto.toStringEliminatingSpaces();
    }

    public String getGenero()
    {
        return genero.toStringEliminatingSpaces();
    }

    public String getNacionalidade()
    {
        return nacionalidade.toStringEliminatingSpaces();
    }

    public String getProvincia()
    {
        return provincia.toStringEliminatingSpaces();
    }

    public String getMunicipio()
    {
        return municipio.toStringEliminatingSpaces();
    }

    public String getComuna()
    {
        return comuna.toStringEliminatingSpaces();
    }

    public boolean getStatus()
    {
        return status;
    }

    // metodos setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setNome(String nome)
    {
        this.nome = new StringBufferModelo(nome, 50);
    }

    public void setContacto(String contacto)
    {
        this.contacto = new StringBufferModelo(contacto, 20);
    }

    public void setGenero(String genero)
    {
        this.genero = new StringBufferModelo(genero, 10);
    }

    public void setNacionalidade(String nacionalidade)
    {
        this.nacionalidade = new StringBufferModelo(nacionalidade, 10);
    }

    public void setProvincia(String provincia)
    {
        this.provincia = new StringBufferModelo(provincia, 20);
    }

    public void setMunicipio(String municipio)
    {
        this.municipio = new StringBufferModelo(municipio, 20);
    }

    public void setComuna(String comuna)
    {
        this.comuna = new StringBufferModelo(comuna, 20);
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }
 
    // metodo toString
    public String toString()
    {
        String dados = "Dados do Cliente Modelo: \n\n";

        dados += "Id: " + getId() + "\n";
        dados += "Nome: " + getNome() + "\n";
        dados += "Contacto: " + getContacto() + "\n";
        dados += "Genero: " + getGenero() + "\n";
        dados += "Nacionalidade: " + getNacionalidade() + "\n";
        dados += "Provincia: " + getProvincia() + "\n";
        dados += "Municipio: " + getMunicipio() + "\n";
        dados += "Comuna: " + getComuna() + "\n";
        dados += "Status: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 150 * 2 + 4 + 1;
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    // metodo write
    public void write(RandomAccessFile stream)
    {
        try
        {
            stream.writeInt(id);
            nome.write(stream);
            contacto.write(stream);
            genero.write(stream);
            nacionalidade.write(stream);
            provincia.write(stream);
            municipio.write(stream);
            comuna.write(stream);
            stream.writeBoolean(status);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao escrever no Ficheiro");
        }
    }

    // metodo read
    public void read(RandomAccessFile stream)
    {
        try
        {
            id = stream.readInt();
            nome.read(stream);
            contacto.read(stream);
            genero.read(stream);
            nacionalidade.read(stream);
            provincia.read(stream);
            municipio.read(stream);
            comuna.read(stream);
            status = stream.readBoolean();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    // metodo salvar
    public void salvar()
    {
        ClienteFile file = new ClienteFile();
        file.salvarDados(this);
    }

    // metodo alterar
    public void salvarDados()
    {
        ClienteFile file = new ClienteFile();
        file.alterarDados(this);
    }

}