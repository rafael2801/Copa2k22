/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.copaqatar;

import java.sql.SQLException;

/**
 *
 * @author supor
 */
public class CopaQatar {

    public static void main(String[] args) throws SQLException {
        Campeonato copa = new Campeonato("Copa do Mundo - Catar 2022");
        copa.gerarEquipesOficiais();
        copa.gerarGruposOficiais(copa.equipes);
        
// Equipe[] equipes = copa.gerarEquipesOficiais();
//            Grupo[] grupos = copa.gerarGruposOficiais(equipes);
// 		for (Equipe e : equipes) {
// 		   System.out.println(e.getNome());
// 		}
//        for (Grupo g : grupos) {
//            g.gerarPartidasGrupo();
//     }
//     for (Grupo g : copa.grupos) {
//           g.getGrupo();
//           System.out.println();
        }
        
    }

