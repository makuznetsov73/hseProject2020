package hse.project.entities.api.mappers;

import hse.project.entities.api.previews.CustomerPreview;
import hse.project.entities.api.previews.VirtualMachinePreview;
import hse.project.entities.mongo.MongoCustomer;
import hse.project.entities.mongo.MongoVirtualMachine;
import hse.project.entities.prototypes.Customer;
import hse.project.entities.prototypes.MapperPrevInterface;
import hse.project.entities.prototypes.VirtualMachine;

public class VirtualMachineMapper implements MapperPrevInterface<VirtualMachine, VirtualMachinePreview> {
	
	@Override
	public void map(VirtualMachine source, VirtualMachine result) {
		result.setGroupName(source.getGroupName());
		result.setId(source.getId());
		result.setLogin(source.getLogin());
		result.setName(source.getName());
		result.setPassword(source.getPassword());
		if (source instanceof MongoVirtualMachine && result instanceof MongoVirtualMachine) {
			MongoVirtualMachine sourceRef = (MongoVirtualMachine) source;
			MongoVirtualMachine resultRef = (MongoVirtualMachine) result;
			resultRef.setEndTime(sourceRef.getEndTime());
			resultRef.setOn(sourceRef.isOn());
			resultRef.setPrice(sourceRef.getPrice());
			resultRef.setStartDate(sourceRef.getStartDate());
			resultRef.setUsername(sourceRef.getUsername());
			resultRef.setGroupId(sourceRef.getGroupId());
			resultRef.setStartPause(sourceRef.getStartPause());
			resultRef.setBlocked(sourceRef.isBlocked());
			resultRef.setIpAddress(sourceRef.getIpAddress());
			resultRef.setSize(sourceRef.getSize());
			resultRef.setSku(sourceRef.getSku());
		}
	}
	
	@Override
	public VirtualMachinePreview mapToPreview(VirtualMachine source) {
		VirtualMachinePreview preview = new VirtualMachinePreview();
		preview.setId(source.getId());
		if (source instanceof MongoVirtualMachine) {
			MongoVirtualMachine sourceRef = (MongoVirtualMachine) source;
			preview.setOn(sourceRef.isOn());
			preview.setUsername(sourceRef.getLogin());
			preview.setIpAddress(sourceRef.getIpAddress());
		}
		return preview;
	}
}
