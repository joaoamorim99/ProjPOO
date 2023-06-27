package Model;

import java.io.Serializable;

public class User  implements Serializable {
    private String email;
    private String pass;
    private int id;
    private String equipa;
    private boolean logged;

    /**
     * Construtor por omissão
     */
    public User() {
        this.email = "";
        this.pass = "";
        this.id = 0;
        this.equipa = "";
        this.logged = false;
    }

    /**
     * Construtor parametrizado
     * @param email String que correponde ao email
     * @param pass String relativa a pass
     * @param id Inteiro relativo ao id
     * @param logged Boolean logado
     */
    public User(String email, String pass, int id, boolean logged) {
        this.email = email;
        this.pass = pass;
        this.id = id;
        this.equipa = "";
        this.logged = logged;
    }

    public User(String email, String pass, int id, String equipa, boolean logged) {
        this.email = email;
        this.pass = pass;
        this.id = id;
        this.equipa = equipa;
        this.logged = logged;
    }

    /**
     * Construtor de cópia.
     * @param myUser Objeto da classe User.
     */
    public User(User myUser) {
        this.email = myUser.getEmail();
        this.pass = myUser.getPass();
        this.id = myUser.getId();
        this.equipa = myUser.getEquipa();
        this.logged = myUser.getLogged();
    }

    //gets e sets

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Método que dá o Id.
     * @return Devolve o Id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Método que define o nome de um User.
     *
     * @param id inteiro representante o identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    public boolean getLogged() {
        return this.logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }


    public String getEquipa() {
        return this.equipa;
    }

    public void setEquipa(String equipa) {
        this.equipa = equipa;
    }

    public void setLogged(String equipa) {
        this.equipa = equipa;
    }

    /**
     * Função que traduz a classe Utilizador.
     *
     * @return Devolve uma String que representa a tradução.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Email : ").append(this.email + "\n");
        sb.append("Pass : ").append(this.pass+ "\n");
        sb.append("Id : ").append(this.id+ "\n");
        sb.append("Equipa : ").append(this.equipa+ "\n");
        sb.append("Logged : ").append(this.logged+ "\n");
        return sb.toString();
    }

    /**
     * Função que verifica se o objeto recebido é idêntico ao da classe Utilizador.
     *
     * @param oneUser Recebe o objeto.
     * @return Devolve um boolean que corresponde à verificação.
     */
    public boolean equals(Object oneUser) {
        if (this == oneUser)
            return true;

        if ((oneUser == null) || (this.getClass() != oneUser.getClass()))
            return false;
        else {
            User user = (User) oneUser;
            return (this.email.equals(user.getEmail())
                    && this.pass.equals(user.getPass())
                    && this.id == user.getId()
                    && this.equipa.equals(user.getEquipa())
                    && this.logged == user.getLogged());
        }
    }

    /**
     * Função que faz um clone da classe User.
     *
     * @return Devolve esse clone.
     */
    public User clone() {
        return new User(this);
    }




}

