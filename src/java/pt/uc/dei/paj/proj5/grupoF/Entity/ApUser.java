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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Grupo F
 */
@Entity
public class ApUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long ApUserId;

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

    public Long getId() {
        return ApUserId;
    }

    public void setId(Long id) {
        this.ApUserId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ApUserId != null ? ApUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApUser)) {
            return false;
        }
        ApUser other = (ApUser) object;
        if ((this.ApUserId == null && other.ApUserId != null) || (this.ApUserId != null && !this.ApUserId.equals(other.ApUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.dei.paj.proj5.grupoF.Entity.ApUser[ id=" + ApUserId + " ]";
    }

}
