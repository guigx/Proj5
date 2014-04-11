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
import pt.uc.dei.paj.proj5.grupoF.Facades.ApUserFacade;
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
    private CartesianChartModel cartesianModel;

    public AverageController() {
    }

    @PostConstruct
    public void initControllerAvg() {
        createCategoryModel();
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

    public List<Object[]> averageAnsEachStudEachProj() {
        return evaluationfacade.avgAdminStudentProject(lg.getCurrentEdition());
    }
}
