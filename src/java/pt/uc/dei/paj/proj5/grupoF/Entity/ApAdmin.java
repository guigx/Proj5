/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Guilherme Pereira
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ApAdmin.getAdminByEmail", query = "SELECT u FROM ApAdmin u WHERE u.email = :email")
})

public class ApAdmin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ApAdminId;

    @NotNull
    @Basic(optional = false)
    @Column(name = "user_name", nullable = false)
    private String name;

    @NotNull
    @Basic(optional = false)
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "Invalid email")
    @Column(nullable = false, unique = true)
    private String email;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private String password;

    public ApAdmin() {
    }

    public Long getApAdminId() {
        return ApAdminId;
    }

    public void setApAdminId(Long ApAdminId) {
        this.ApAdminId = ApAdminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ApAdminId != null ? ApAdminId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the ApAdminId fields are not set
        if (!(object instanceof ApAdmin)) {
            return false;
        }
        ApAdmin other = (ApAdmin) object;
        if ((this.ApAdminId == null && other.ApAdminId != null) || (this.ApAdminId != null && !this.ApAdminId.equals(other.ApAdminId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.paj.proj5.grupoF.Entity.ApAdmin[ id=" + ApAdminId + " ]";
    }

}
