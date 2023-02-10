package pe.edu.idat.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.idat.entity.PlanSla;
import pe.edu.idat.repository.IPlanSlaRepo;
import pe.edu.idat.services.IPlanSlaService;

@Service
public class PlanSlaServiceImpl implements IPlanSlaService{
	
	@Autowired
	private IPlanSlaRepo planSlaRepo;

	@Override
	@Transactional(readOnly = true)
	public List<PlanSla> findAll() {
		// TODO Auto-generated method stub
		return planSlaRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PlanSla findById(Long idPlanSla) {
		// TODO Auto-generated method stub
		return planSlaRepo.findById(idPlanSla).orElse(null);
	}

	@Override
	@Transactional
	public PlanSla save(PlanSla planSla) {
		// TODO Auto-generated method stub
		return planSlaRepo.save(planSla);
	}

	@Override
	@Transactional
	public void delete(Long idPlanSla) {
		// TODO Auto-generated method stub
		planSlaRepo.deleteById(idPlanSla);
		
	}
	
	

}
