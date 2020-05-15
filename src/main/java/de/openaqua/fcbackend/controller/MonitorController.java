package de.openaqua.fcbackend.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.openaqua.fcbackend.SerialGenerator;
import de.openaqua.fcbackend.entities.MonitorResponse;
import de.openaqua.fcbackend.entities.MonitorStatus;
import de.openaqua.fcbackend.redis.SessionRepository;
import de.openaqua.fcbackend.redis.RedisSession;

@RestController
@RequestMapping(path = "/monitor")
@ComponentScan("de.openaqua.fcbackend.controller")
public class MonitorController {
	private static final Logger LOG = LoggerFactory.getLogger(MonitorController.class);

	@Autowired
	private SessionRepository redis;

	@Autowired
	private SerialGenerator serialGenerator;

	private MonitorStatus checkRedis() {
		MonitorStatus result = MonitorStatus.UNKNOWN;

		// Store and retrieve some data from redis to test redis
		try {
			RedisSession session = new RedisSession(serialGenerator.getNext());

			Optional<RedisSession> o = redis.findById(session.getId());
			if (o.isPresent()) {
				LOG.error("Redis has a session object with id {} which should not exists", session.getId());
				return MonitorStatus.FAILURE;
			}
			session = redis.save(session);
			Optional<RedisSession> o2 = redis.findById(session.getId());
			if (!o2.isPresent()) {
				LOG.error("Redis has no session object with id {} which should exists", session.getId());
				return MonitorStatus.FAILURE;
			}
			redis.delete(session);

			// some stuff for debug
			Iterable<RedisSession> oldSessions = redis.findAll();
			oldSessions.forEach(s -> LOG.debug("old but known SessionIds are: {}", s.getId()));

		} catch (org.springframework.data.redis.RedisConnectionFailureException e) {
			LOG.error("Redis Exception: {}", e.getLocalizedMessage());
			return MonitorStatus.FAILURE;
		}

		return MonitorStatus.OK;
	}

	@GetMapping()
	public MonitorResponse index() {
		LOG.info("GET /");

		// create default response
		MonitorResponse response = new MonitorResponse();
		response.setStatusDatabase(MonitorStatus.UNKNOWN);
		response.setStatusSystem(MonitorStatus.OK);
		response.setStatusRedis(checkRedis());

		// return
		return response;
	}
}
