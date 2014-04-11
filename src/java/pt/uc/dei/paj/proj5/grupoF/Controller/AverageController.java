/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.dei.paj.proj5.grupoF.Controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import pt.uc.dei.paj.proj5.grupoF.EJB.LoggedUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.ApUser;
import pt.uc.dei.paj.proj5.grupoF.Entity.Edition;
import pt.uc.dei.paj.proj5.grupoF.Facades.ApUserFacade;
import pt.uc.dei.paj.proj5.grupoF.Facades.EditionFacade;
import pt.uc.dei.paj.proj5.grupoF.Facades.EvaluationFacade;

/**
 *
 * @author Guilherme Pereira
 */
@Named
@RequestScoped
public class AverageController {

    @Inject
    private EvaluationFacade evaluationfacade;
    @Inject
    private LoggedUser lg;
    @Inject
    private ApUserFacade apuserfacade;
    @Inject
    private EditionFacade editionfacade;
    private CartesianChartModel cartesianModel;
    private CartesianChartModel cartesianModel1;
    private CartesianChartModel cartesianModel2;

    public AverageController() {
    }

    @PostConstruct
    public void initControllerAvg() {
        createCategoryModel();
        createCategoryModel1();
    }

    public CartesianChartModel getCartesianModel2() {
        return cartesianModel2;
    }

    public void setCartesianModel2(CartesianChartModel cartesianModel2) {
        this.cartesianModel2 = cartesianModel2;
    }

    public EditionFacade getEditionfacade() {
        return editionfacade;
    }

    public void setEditionfacade(EditionFacade editionfacade) {
        this.editionfacade = editionfacade;
    }

    public CartesianChartModel getCartesianModel1() {
        return cartesianModel1;
    }

    public void setCartesianModel1(CartesianChartModel cartesianModel1) {
        this.cartesianModel1 = cartesianModel1;
    }

    public EvaluationFacade getEvaluationfacade() {
        return evaluationfacade;
    }

    public void setEvaluationfacade(EvaluationFacade evaluationfacade) {
        this.evaluationfacade = evaluationfacade;
    }

    public LoggedUser getLg() {
        return lg;
    }

    public void setLg(LoggedUser lg) {
        this.lg = lg;
    }

    public ApUserFacade getApuserfacade() {
        return apuserfacade;
    }

    public void setApuserfacade(ApUserFacade apuserfacade) {
        this.apuserfacade = apuserfacade;
    }

    public CartesianChartModel getCartesianModel() {
        return cartesianModel;
    }

    public void setCartesianModel(CartesianChartModel cartesianModel) {
        this.cartesianModel = cartesianModel;
    }

    public void createCategoryModel() {
        List<ApUser> list = apuserfacade.apuserByEdition(this.lg.getCurrentEdition());
        cartesianModel = new CartesianChartModel();
        for (int i = 0; i < list.size(); i++) {
            ChartSeries a = new ChartSeries();
            String est = list.get(i).getName();
            a.setLabel(est);
            List<Object[]> data = evaluationfacade.avgAdminStdEachProj(list.get(i));
            if (!data.isEmpty()) {
                a.setLabel(est);
                for (int j = 0; j < data.size(); j++) {
                    Double avg = (Double) data.get(j)[0];
                    String proj = (String) data.get(j)[1];
                    a.set(proj, avg);
                }
                cartesianModel.addSeries(a);
            }
        }
    }

    public void createCategoryModel1() {
        cartesianModel1 = new CartesianChartModel();

        for (Edition e : editionfacade.findAll()) {
            ChartSeries a = new ChartSeries();
            a.setLabel(e.getName());
            List<Object[]> data = evaluationfacade.avgEachProjEdition(e);
            for (Object[] o : data) {
                Double avg = (Double) o[0];
                a.set(e.getName(), avg);

            }
            cartesianModel1.addSeries(a);
        }

//        List<Edition> list = editionfacade.findAll();
//        cartesianModel = new CartesianChartModel();
//        for (int i = 0; i < list.size(); i++) {
//            ChartSeries a = new ChartSeries();
//            String est = list.get(i).getName();
//            a.setLabel(est);
//            List<Object[]> data = evaluationfacade.avgEachProjEdition(list.get(i));
//            if (!data.isEmpty()) {
//                a.setLabel(est);
//                for (int j = 0; j < data.size(); j++) {
//                    Double avg = (Double) data.get(j)[0];
//                    String proj = (String) data.get(j)[1];
//                    a.set(proj, avg);
//                }
//                cartesianModel.addSeries(a);
//            }
//        }
    }

    public List<Object[]> averageEachStudentEachProject() {
        return evaluationfacade.avgAdminStudentProject(lg.getCurrentEdition());
    }

    public List<Object[]> averageProjectEachEdition() {
        return evaluationfacade.avgEachProjEdition(lg.getCurrentEdition());
    }

    public List<Object[]> avgEachCriterionEachProj() {
        return evaluationfacade.avgEachCriterionEachProj(lg.getCurrentEdition());
    }

    public List<Object[]> avgEachCriterionInEdition() {
        return evaluationfacade.avgEachCriterionInEdition(lg.getCurrentEdition());
    }

    public List<Object[]> avgStudentEachCriterionEdition() {
        return evaluationfacade.avgStudentEachCriterionEdition(lg.getLoggedUser().getApUserId());
    }

    public List<Object[]> avgEachProjInEdition() {
        return evaluationfacade.avgEachProjInEdition(lg.getLoggedUser().getEdition());
    }
}
