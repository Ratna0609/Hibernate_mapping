package com.org.hibernate.example;

import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FetchData {
	public static void main(String[] args) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction t = session.beginTransaction();
			TypedQuery query = session.createQuery("from Question");
			List<Question> list = query.getResultList();

			Iterator<Question> itr = list.iterator();
			while (itr.hasNext()) {
				Question q = itr.next();
				System.out.println("Question Name: " + q.getQname());

				// printing answers
				List<Answer> list2 = q.getAnswers();
				Iterator<Answer> itr2 = list2.iterator();
				while (itr2.hasNext()) {
					Answer a = itr2.next();
					System.out.println(a.getAnswername() + ":" + a.getPostedBy());
				}
			}
			session.close();
			System.out.println("success");
		}
	}
}