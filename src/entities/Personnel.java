/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "IdPersonnel")
    private String idPersonnel;

    @Basic(optional = false)
    @Column(name = "NomPersonnel")
    private String nomPersonnel;

    @Basic(optional = false)
    @Column(name = "EmailPersonnel")
    private String emailPersonnel;

    @Column(name = "MotDePasse")
    private String motDePasse;
    
    @Column(name = "Role")
    private String role;

    public String getRole() {
        return role;
}

    public void setRole(String role) {
        this.role = role;
}

    public Personnel() {
    }

    public Personnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    public String getEmailPersonnel() {
        return emailPersonnel;
    }

    public void setEmailPersonnel(String emailPersonnel) {
        this.emailPersonnel = emailPersonnel;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersonnel != null ? idPersonnel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Personnel)) {
            return false;
        }
        Personnel other = (Personnel) object;
        if ((this.idPersonnel == null && other.idPersonnel != null) || (this.idPersonnel != null && !this.idPersonnel.equals(other.idPersonnel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personnel[ idPersonnel=" + idPersonnel + " ]";
    }
}