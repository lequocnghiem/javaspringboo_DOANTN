package com.api.thuctaptotnghiepbackend.Repository.Role;

import java.util.List;

import org.hibernate.query.NativeQuery;
import org.hibernate.Session; // Import thêm Session
import org.hibernate.transform.Transformers; // Import thêm Transformers
import org.hibernate.type.StandardBasicTypes; // Import thêm StandardBasicTypes
import org.springframework.stereotype.Repository;

import com.api.thuctaptotnghiepbackend.Entity.Role;
import com.api.thuctaptotnghiepbackend.Entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RoleCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRoles(User user) {
        StringBuilder sql = new StringBuilder()
            .append("SELECT r.name AS name FROM db_user u " +
                    "JOIN user_roles ur ON u.id = ur.user_id " +
                    "JOIN role r ON r.id = ur.role_id ");
        sql.append("WHERE 1=1 ");
        
        if (user.getEmail() != null) {
            sql.append("AND u.email = :email");
        }

        NativeQuery<Role> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());
        
        if (user.getEmail() != null) {
            query.setParameter("email", user.getEmail());
        }

        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Role.class));

        return query.getResultList();
    }
}
