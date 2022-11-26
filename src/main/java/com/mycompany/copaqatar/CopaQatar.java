/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.copaqatar;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author supor
 */
public class CopaQatar {

    public static void main(String[] args) throws SQLException {
        DAO dao = new DAO();
        Campeonato c = new Campeonato();
        //c.cadastrarEquipesOficiais();
        //c.setGrupos();
        //c.cadastrarGruposOficiais();
        //Classificacao x = dao.carregarClassificacaoEquipe(c.getEquipes()[0]);
        TelaInicial ti = new TelaInicial();
        ti.setVisible(true);
        //System.out.println(x.getEquipe().getNome());
        // System.out.println(c.getGrupos()[0].getNome());
        //c.cadastrarGruposOficiais();
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
//        }
        
    }
}
