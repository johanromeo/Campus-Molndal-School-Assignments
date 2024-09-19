package com.johan.pojos;

import lombok.*;
/**A common Address POJO needed by both the client-side and the server-side modules*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private Address address;
    @Override
    public String toString() {
        return  "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\n\nAddress " + address;
    }
}