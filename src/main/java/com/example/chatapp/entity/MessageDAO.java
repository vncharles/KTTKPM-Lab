package com.example.chatapp.entity;

import com.example.chatapp.utils.HibernateUtils;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    private Session session;

    public MessageDAO() {
        session = HibernateUtils.getSessionFactory().openSession();
    }

    public boolean add(Message message) {
        Transaction tr = session.getTransaction();
        tr.begin();

        try {
            Query query = session.createNativeQuery("INSERT INTO messages (user, message) VALUES ('"+message.getUser()+"', '"+message.getMessage()+"');");
            query.executeUpdate();
            tr.commit();

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            tr.rollback();
        }

        return false;
    }

    public List<Message> listMessage() {
        Transaction tr = session.getTransaction();
        tr.begin();

        try {
            Query query = session.createNativeQuery("SELECT * FROM messages;");
            List<Object[]> resultList = query.getResultList();

            List<Message> messages = new ArrayList<>();
            resultList.forEach(i -> {
                messages.add(new Message((Long) i[0], (String) i[1], (String) i[2]));
            });

            tr.commit();
            return messages;
        } catch (Exception exception) {
            exception.printStackTrace();
            tr.rollback();
        }

        return null;
    }
}
