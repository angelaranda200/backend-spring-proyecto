package pe.edu.idat.services;

import java.util.List;

import pe.edu.idat.entity.PlanSla;

public interface IPlanSlaService {
	
	public List<PlanSla> findAll();
	
	public PlanSla findById(Long idPlanSla);
	
	public PlanSla save(PlanSla planSla);
	
	public void delete(Long idPlanSla);

}
