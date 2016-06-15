package com.example.anurag.activeandroidlibrarycrud;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Anurag on 6/15/2016.
 */
@Table(name ="City")
public class City extends Model {

    @Column(name ="NAME")
    public String name;

    @Column(name="Country")
    public Country country;
}
