package com.example.anurag.activeandroidlibrarycrud;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by Anurag on 6/16/2016.
 */
@Table(name ="Country")
public class Country extends Model {
    @Column(name="name")
    public String name;

    @Column(name ="pm")
    public String pm;

    public List<City> cities()
    {
        return getMany(City.class,"Country");
    }
}
