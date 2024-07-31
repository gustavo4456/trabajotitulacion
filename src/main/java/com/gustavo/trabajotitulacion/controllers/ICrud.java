/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gustavo.trabajotitulacion.controllers;

/**
 *
 * @author gustavo
 */
import java.util.List;
import java.util.Optional;

public interface ICrud<T> {

    public boolean insertObject(T entity);

    public boolean deleteObject(int id);

    public Optional<T> getObject(int id);

    public boolean modifiedObject(T entity);

    public List<T> getAllObjects();
}
