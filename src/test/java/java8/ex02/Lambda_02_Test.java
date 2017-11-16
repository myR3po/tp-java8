package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    interface PersonToObjectMapper<T> {
        T map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    @SuppressWarnings("unchecked")
	private <T> List<T> map(List<Person> personList, PersonToObjectMapper<T> mapper) {
        // TODO implémenter la méthode pour transformer une liste de personnes en liste de comptes
    	List<T> aList = new ArrayList<T>();
    	for(Person person : personList) {
    		aList.add((T) mapper.map(person));
    	}
        return aList;
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        PersonToObjectMapper<Account> personToAccountMapper = p -> { 
        	Account a = new Account();
        	a.setBalance(100); 
        	a.setOwner(p); 
        	return a;
        	}; 
        
        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        List<Account> result = map(personList, personToAccountMapper);

        assert result.size() == personList.size();
        for (Account account : result) {
            assert account.getBalance().equals(100);
            assert account.getOwner() != null;
        }
    }
    // end::test_map_person_to_account[]

    
    
    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        PersonToObjectMapper<String> personToObjectMapper = p -> { 
        	return  p.getFirstname();
        	}; 
        	
        // TODO transformer la liste de personnes en liste de prénoms
        List<String> result = map(personList, personToObjectMapper);

        assert result.size() == personList.size();
        for (String firstname : result) {
            assert firstname.startsWith("first");
        }
    }
    // end::test_map_person_to_firstname[]
}
