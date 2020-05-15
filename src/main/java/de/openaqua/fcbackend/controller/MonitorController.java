package de.openaqua.fcbackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.openaqua.fcbackend.entities.MonitorResponse;
import de.openaqua.fcbackend.entities.MonitorStatus;

@RestController
@RequestMapping(path = "/monitor")
public class MonitorController {
	private Logger log = LoggerFactory.getLogger(MonitorController.class);

	@GetMapping()
	public MonitorResponse index() {
		log.info("GET /");
		MonitorResponse response = new MonitorResponse();
		response.setStatusDatabase(MonitorStatus.UNKNOWN);
		response.setStatusSystem(MonitorStatus.OK);
		response.setStatusRedis(MonitorStatus.OK);
		return response;
	}
}
