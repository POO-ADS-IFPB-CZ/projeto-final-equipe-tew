package dao;

import model.Conteudo;
import model.Usuario;

import java.io.*;
import java.util.*;

public class ConteudoDao {
    private static final String USUARIOS_FILE = "usuarios.ser";
    private static final String CONTEUDOS_FILE = "conteudos.ser";
    private List<Usuario> usuarios;
    private List<Conteudo> conteudos;

    public ConteudoDao() {
        this.usuarios = carregarUsuarios();
        this.conteudos = carregarConteudos();
    }
    public Usuario autenticarOuCadastrarUsuario(String email, String senha, String nome) {
        if (buscarUsuario(email) == null && (nome == null || nome.isEmpty())) {
            System.out.println("Nome não pode ser vazio para novos cadastros.");
            return null;
        }

        Usuario usuario = buscarUsuario(email);

        if (usuario == null) {
            usuario = new Usuario(nome, email, senha);
            adicionarUsuario(usuario);
            salvarUsuarios();
            System.out.println("Novo usuário cadastrado com sucesso!");
        } else if (!usuario.verificarSenha(senha)) {
            System.out.println("Senha incorreta.");
            return null;
        }

        return usuario;
    }

    public Usuario buscarUsuario(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    private List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(USUARIOS_FILE);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                usuarios = (List<Usuario>) ois.readObject();
            }
            catch (EOFException e) {
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }

    public void salvarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USUARIOS_FILE))) {
            oos.writeObject(usuarios);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Conteudo> carregarConteudos() {
        List<Conteudo> conteudos = new ArrayList<>();
        File file = new File(CONTEUDOS_FILE);
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                conteudos = (List<Conteudo>) ois.readObject();
            }
            catch (EOFException e) {
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conteudos;
    }

    public void adicionarConteudo(Conteudo conteudo) {
        conteudos.add(conteudo);
        salvarConteudos();
    }

    private void salvarConteudos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CONTEUDOS_FILE))) {
            oos.writeObject(conteudos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
