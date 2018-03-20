// IMyAidlInterface.aidl
package com.myaidl;
// Declare any non-default types here with import statements
import com.myaidl.Person;
interface IMyAidlInterface {
    void addPerson(in Person person);
     List<Person> getPersonList();
}
